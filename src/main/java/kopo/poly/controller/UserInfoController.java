package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Executable;

@RequestMapping(value = "user")
@Controller
@Slf4j
public class UserInfoController {

    @Resource(name = "UserInfoService")
    private IUserInfoService userInfoService;

    @GetMapping(value = "userRegForm")
    public String userRegForm() {
        return "/user/UserRegForm";
    }

    @PostMapping(value = "insertUserInfo")
    public String insertUserInfo(HttpServletRequest request, ModelMap model) throws Exception{
        String msg = "";
        UserInfoDTO pDTO = null;

        try {
            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            String user_name = CmmUtil.nvl(request.getParameter("user_name"));
            String password = CmmUtil.nvl(request.getParameter("password"));
            String email = CmmUtil.nvl(request.getParameter("email"));
            String addr1 = CmmUtil.nvl(request.getParameter("addr1"));
            String addr2 = CmmUtil.nvl(request.getParameter("addr2"));

            pDTO = new UserInfoDTO();
            pDTO.setUserId(user_id);
            pDTO.setUserName(user_name);
            pDTO.setPassword(EncryptUtil.encHashSHA256(password));
            pDTO.setAddr1(addr1);
            pDTO.setAddr2(addr2);
            pDTO.setEmail(EncryptUtil.encAES128CBC(email));

            int res = userInfoService.insertUserInfo(pDTO);

            if (res==1){
                msg = "회원가입이되었습니다.";
            }else if (res == 2){
                msg = "이미 가입된 이메일 주소입니다.";
            }else {
                msg = "오류로 인해 회원가입에 실패하였습니다.";
            }
            } catch (Exception e){
            msg = "실패하였습니다" + e;
            e.printStackTrace();
        }finally {
            model.addAttribute("msg", msg);
            model.addAttribute("pDTO", pDTO);

            pDTO = null;
        }
    return "/user/UserRegSuccess";
    }

    @GetMapping("loginForm")
    public String loginForm() throws Exception{
        return "/user/LoginForm";
    }

    @PostMapping("getUserLoginCheck")
    public String getUserLoginCheck(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception{
        int res = 0;

        UserInfoDTO pDTO = null;

        try {
            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            String password = CmmUtil.nvl(request.getParameter("password"));

            pDTO = new UserInfoDTO();
            pDTO.setUserId(user_id);
            pDTO.setPassword(EncryptUtil.encHashSHA256(password));

            res = userInfoService.getUserLoginCheck(pDTO);

            if (res == 1){
                session.setAttribute("SS_USER_ID", user_id);
            }
        }catch (Exception e){
            res = 2;
            e.printStackTrace();
        }finally {
            model.addAttribute("res", String.valueOf(res));

            pDTO = null;
        }
        return "/user/LoginResult";
    }

}
