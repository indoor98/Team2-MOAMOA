package team2.proto.service.post;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team2.proto.dto.hashtag.HashtagRequestDTO;
import team2.proto.dto.post.*;
import team2.proto.entity.*;
import team2.proto.repository.post.PostRepository;
import team2.proto.repository.post.PostUserRepository;
import team2.proto.service.authentication.JwtService;
import team2.proto.service.authentication.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostUserRepository postUserRepository;
    private final UserService userService;
    private final JwtService jwtService ;

    // 게시글 작성
    @Transactional
    @Override
    public Long createPost(PostWriteDTO params, String userEmail) {
        // 유저 이메일로부터 해당 유저의 ID를 가져옴
        String userNickname = userService.findByEmail(userEmail).getNickname();
        User user = userService.findByEmail(userEmail);
        System.out.println("DEBUG >>>> PostService::createPost");
        // dto -> entity
        Post post = Post.builder()
                .title(params.getTitle())
                .price(params.getPrice())
                .headCount(params.getHeadCount())
                .deadline(params.getDeadLine())
                .receivePlace(params.getReceivePlace())
                .productUrl(params.getProductUrl())
                .writer(userNickname)
                .deleteYn(false) // 기본값을 false로 설정
                .build();

        // 해시태그 추가 하기
        List<HashtagRequestDTO> hashtagList = params.getHashtagList();
        for(HashtagRequestDTO hashtagRequestDTO : hashtagList) {
            System.out.println("DEBUG >>>> PostServece: hashttageRequestDTO : " + hashtagRequestDTO.getHashtag());
            Hashtag hashtag = Hashtag.createHashtag(hashtagRequestDTO.getHashtag(), post);
            post.addHashtag(hashtag);
           }
        postRepository.save(post);

        // PostUserDTO에서 정보 가져와서 PostUser 엔티티 생성
        PostUserDTO postUserDTO = new PostUserDTO(true, post, user);
        PostUser postUser = convertToEntity(postUserDTO);

        // PostUser 엔티티 저장
        postUserRepository.save(postUser);
        return post.getId();
    }

    private PostUser convertToEntity(PostUserDTO postUserDTO) {
        return PostUser.builder()
                .id(new PostUserPK(postUserDTO.getUser(), postUserDTO.getPost()))
                .isHost(postUserDTO.isHost())
                .post(postUserDTO.getPost())
                .user(postUserDTO.getUser())
                .build();
    }

    private List<HashtagRequestDTO> convertHashtagEntityToDto(List<Hashtag> hashtagList) {
        List<HashtagRequestDTO> hashtagRequestDTOList = new ArrayList<HashtagRequestDTO>();
        for(Hashtag hashtag : hashtagList) {
            hashtagRequestDTOList.add(new HashtagRequestDTO(hashtag.getHashtag()));
        }
        return hashtagRequestDTOList;
    }

    // 단일 게시글 조회
    @Override
    @Transactional(readOnly = true)
    public PostResponseDTO getPostById(Long id, String userEmail) {
        Post post = postRepository.findByIdAndDeleteYnFalse(id);

        if (post == null) {
            // 삭제 여부가 false인 게시글이 없을 경우 예외 처리 또는 null 처리
            // 여기서는 null을 리턴하도록 하겠습니다.
            return null;
        }
        long joinedUsersCount = postUserRepository.countByPostId(id);
        User currentUser = userService.findByEmail(userEmail);
        boolean isJoined = postUserRepository.existsByPostIdAndUserId(id, currentUser.getId());


        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getPrice(),
                post.getHeadCount(),
                joinedUsersCount,
                post.getDeadline(),
                post.getReceivePlace(),
                post.getProductUrl(),
                convertHashtagEntityToDto(post.getHashtagList()),
                isJoined
                );
    }


    public List<Hashtag> convertHashtagDtoToEntity(List<HashtagRequestDTO> hashtagRequestDTOList, Post post) {
        List<Hashtag> hashtagList = new ArrayList<Hashtag>();
        for (HashtagRequestDTO dto : hashtagRequestDTOList) {
            hashtagList.add(Hashtag.builder()
                    .hashtag(dto.getHashtag())
                    .post(post)
                    .build());
        }
        return hashtagList;
    }
    // 게시글 수정
    @Override
    public void updatePost(Long id, PostUpdateDTO param) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(param.getTitle());
        post.setPrice(param.getPrice());
        post.setHeadCount(param.getHeadCount());
        post.setDeadline(param.getDeadLine());
        post.setReceivePlace(param.getReceivePlace());
        post.setProductUrl(param.getProductUrl());
        post.setUpdateDate(LocalDateTime.now());

        post.resetHashtag();
        for (HashtagRequestDTO dto : param.getHashtagList()) {
            Hashtag hashtag = Hashtag.builder()
                    .hashtag(dto.getHashtag())
                    .post(post)
                    .build();
            post.addHashtag(hashtag);
        }
//        post.setHashtagList(convertHashtagDtoToEntity(param.getHashtagList(), post));

        postRepository.save(post);
    }

    // 전체 게시글 조회
    @Override
    @Transactional(readOnly = true)
    public List<PostListResponseDTO> getAllPosts(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.by("id").descending());
        Page<Post> posts = postRepository.findAllByDeleteYnIsFalse(pageable);
        System.out.println("getallposts serviceimpl posts" + posts.getPageable());

        // null 값을 무시하고 false인 데이터만 필터링하여 반환
        return posts.stream()
                .filter(post -> post.getDeleteYn() != null && !post.getDeleteYn())
                .map(post -> new PostListResponseDTO(post.getTitle(), post.getPrice(), post.getHeadCount(),
                        post.getDeadline(), post.getReceivePlace(), post.getProductUrl(), convertHashtagEntityToDto(post.getHashtagList())))
                .collect(Collectors.toList());
    }


    // 게시글 삭제
    @Transactional
    @Override
    public void delete(Long postId, User user) {
        // 게시글을 불러옵니다.
        Post post = postRepository.findById(postId).orElseThrow();

        // 사용자가 게시글 작성자인지 확인합니다.
        PostUser postUser = postUserRepository.findByPostAndUserAndIsHost(post, user, true);
        if (postUser == null) {
            // 게시글 작성자가 아닌 경우 예외 처리
            throw new IllegalArgumentException("게시글 작성자만 게시글을 삭제할 수 있습니다.");
        }

        // 삭제 여부 플래그를 설정하고 엔티티를 저장합니다.
        post.setDeleteYn(true);
        postRepository.save(post);

        // PostUser 엔티티에 deleteYn 필드를 설정하여 삭제되었다는 것을 나타냅니다.
        List<PostUser> postUsers = postUserRepository.findByPost(post);
        for (PostUser users : postUsers) {
            users.setDeleteYn(true);
        }
    }


    // 공동구매 참여
    @Transactional
    @Override
    public void join(Long postId, HttpServletRequest request) {
        // 게시글 조회
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다."));

        // 게시글의 headCount 조회
        int headCount = post.getHeadCount();

        // 해당 게시글에 참여한 유저들의 수 조회
        long joinedUsersCount = postUserRepository.countByPostId(postId);

        // 현재 사용자의 정보 가져오기
        String currentUserEmail = jwtService.extractUserName(jwtService.extractTokenFromRequest(request));
        User currentUser = userService.findByEmail(currentUserEmail);

        // 현재 사용자가 해당 게시글에 이미 참여하고 있는지 확인
        boolean alreadyJoined = postUserRepository.existsByPostIdAndUserId(postId, currentUser.getId());
        if (alreadyJoined) {
            throw new IllegalArgumentException("이미 해당 공동구매에 참여하였습니다.");
        }

        // PostUserDTO에서 정보 가져와서 PostUser 엔티티 생성
        boolean isHost = false; // 공동구매의 주최자가 아닌 경우
        PostUserDTO postUserDTO = new PostUserDTO(isHost, post, currentUser);
        PostUser postUser = convertToEntity(postUserDTO);

        // PostUser 엔티티 저장
        postUserRepository.save(postUser);
    }


    // 공동구매 참여 취소
    @Transactional
    @Override
    public void cancel(Long postId, HttpServletRequest request) {
        PostUser postUser = postUserRepository.findByPostIdAndIsHost(postId, false);
        if (postUser != null) {
            postUserRepository.deleteByPostIdAndIsHost(postId, false);
        }
    }

    // 단일 Hashtag 사용하여 조회하는 함수
    @Override
    @Transactional(readOnly = true)
    public List<PostListResponseDTO> getAllPostsByHashtag(Integer pageNo, String hashtag) {
        System.out.println("DEBUG >>>> PostService::getAllPostsByHashtag");
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.by("id").descending());
        Page<Post> posts = postRepository.findAllByHashtag(hashtag, pageable);

        // null 값을 무시하고 false인 데이터만 필터링하여 반환
        return posts.stream()
                .filter(post -> post.getDeleteYn() != null && !post.getDeleteYn())
                .map(post -> new PostListResponseDTO(post.getTitle(), post.getPrice(), post.getHeadCount(),
                        post.getDeadline(), post.getReceivePlace(), post.getProductUrl(), convertHashtagEntityToDto(post.getHashtagList())))
                .collect(Collectors.toList());
    }


}
