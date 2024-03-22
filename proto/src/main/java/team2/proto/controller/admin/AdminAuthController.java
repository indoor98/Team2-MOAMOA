package team2.proto.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team2.proto.entity.AdminPage;
import team2.proto.service.admin.AdminAuthService;

import java.util.List;

@Controller
@RequestMapping("/api/admin")
public class AdminAuthController {

    @Autowired
    private AdminAuthService service;

    @GetMapping("/authlist")
    public  ResponseEntity<List<AdminPage>> getAllAuthList() {
        System.out.println("AdminAuthController.getAllAuthList");
        List<AdminPage> list = service.getAllAuthList();
        return new ResponseEntity<List<AdminPage>>(list,HttpStatus.OK);
    }

    @PutMapping("/authlist/{authNo}")
    public ResponseEntity<Void> auth() {

        return null;
    }
}