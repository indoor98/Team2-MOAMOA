package team2.proto.service.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team2.proto.dto.mypage.MyPageCommentResponseDTO;
import team2.proto.dto.mypage.MyPagePostResponseDTO;
import team2.proto.dto.mypage.MyPageUserResponseDTO;
import team2.proto.entity.Comment;
import team2.proto.entity.Post;
import team2.proto.entity.PostUser;
import team2.proto.entity.User;
import team2.proto.repository.user.UserRepository;
import team2.proto.repository.mypage.MyPageUserCommentRepository;
import team2.proto.repository.mypage.MypageJoinPostRepository;
import team2.proto.repository.mypage.MypagePostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("mypage")
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService{

    private final UserRepository userRepository;

    private final MypageJoinPostRepository joinPostRepository;

    private final MyPageUserCommentRepository commentRepository;

    private final MypagePostRepository postRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private MyPageUserResponseDTO dto = new MyPageUserResponseDTO();


    @Override
    public MyPageUserResponseDTO findUserById(String email){
        System.out.println("PostServiceImpl.findUserById");

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            User responseUser = user.get();

            dto.setId(responseUser.getId());
            dto.setSchool(responseUser.getSchool());
            dto.setPhone(responseUser.getPhone());
            dto.setEmail(responseUser.getEmail());
            dto.setAddress(responseUser.getAddress());
            dto.setNickname(responseUser.getNickname());
            System.out.println(dto.toString());
        } else {

        }
        return dto;
    }

    @Override
    public void updateNickName(String email, String nickName){
        System.out.println("MyPageServiceImpl.updateNickName");

        System.out.println(nickName);

        userRepository.updateNickName(email, nickName);
    }

    @Override
    public void updatePassword(String email, String newPwd){
        System.out.println("MyPageServiceImpl.updatePwd");

        User user = userRepository.findByEmail(email).get();
        user.setPassword(bCryptPasswordEncoder.encode(newPwd));
        userRepository.save(user);
    }

    // 본인이 작성한 게시글 조회
    @Override
    public List<MyPagePostResponseDTO> findPostByUser(String email) {

        System.out.println("MyPageServiceImpl.findPostByUser");
        List<MyPagePostResponseDTO> dtoList = new ArrayList<>();

        // 엔티티로 생성
        List<PostUser> postUser = postRepository.findByIsHostTrue(email);

        // POST 가져오기
        List<Post> posts = postUser.stream()
                .map(PostUser::getPost) // PostUser 엔티티에서 Post 엔티티를 추출
                .collect(Collectors.toList()); // 새로운 리스트로 변환하여 반환

        System.out.println("success");
        for(Post post : posts){
            MyPagePostResponseDTO dto = new MyPagePostResponseDTO();
            dto.setPostId(post.getId());
            dto.setProductUrl(post.getProductUrl());
            dto.setDeadLine(post.getDeadline());
            dto.setPrice(post.getPrice());
            dto.setTitle(post.getTitle());
            dto.setNickname(findUserById(email).getNickname());
            dto.setHeadCount(post.getHeadCount());
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public List<MyPageCommentResponseDTO> findCommentByUser(String email){

        System.out.println("MyPageServiceImpl.findCommentByUser");

        List<MyPageCommentResponseDTO> dtoList = new ArrayList<>();
        List<Comment> comments = commentRepository.findByEmail(email);

        // db에 저장된 회원별 comment 개수 대로는 출력이 되는데, 첫 값만 반복되어서 출력됌.
        // dto는 한번 생성되면, 유지되므로, 항상 새로 생성해야 한다.
        for(Comment comment : comments){
            MyPageCommentResponseDTO dto = new MyPageCommentResponseDTO();
            dto.setContent(comment.getContent());
            dto.setPostTitle(comment.getPost().getTitle());
            dtoList.add(dto);
        }
        return dtoList;

    }


    // 회원이 참여한 모든 게시글
    @Override
    public List<MyPagePostResponseDTO> joinPost(String email){
        System.out.println("MyPageServiceImpl.particiPost");

        // 엔티티로 생성
        List<PostUser> postUser = joinPostRepository.findByUserEmail(email);

        // POST 가져오기
        List<Post> posts = postUser.stream()
                .map(PostUser::getPost) // PostUser 엔티티에서 Post 엔티티를 추출
                .collect(Collectors.toList()); // 새로운 리스트로 변환하여 반환

        System.out.println(posts.toArray().toString());
        System.out.println("success");

        List<MyPagePostResponseDTO> dtoList = new ArrayList<>();
        for(Post post : posts){
            MyPagePostResponseDTO dto = new MyPagePostResponseDTO();
            dto.setPostId(post.getId());
            dto.setProductUrl(post.getProductUrl());
            dto.setDeadLine(post.getDeadline());
            dto.setPrice(post.getPrice());
            dto.setTitle(post.getTitle());
            dto.setNickname(findUserById(email).getNickname());
            dto.setHeadCount(post.getHeadCount());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
