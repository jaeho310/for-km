package com.example.projectsample.application.service;

import com.example.projectsample.interfaces.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 메서드
     * @param member 사용자정보를 담는 dto
     * @return
     */
    public Object insertMember(MemberDto member) {
//        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        int insertCnt = userDao.insertMember(userDto);
//        if (insertCnt != 1) {
//            throw new RuntimeException("필수정보를 모두 입력해주세요.");
//        }
        System.out.println(passwordEncoder.encode("abc"));
        return "success";
    }

    public boolean isDuplicated(String memberId) {
//        return userDao.checkIdDuplicate(memberId) == 1;

        return false;
    }

    /**
     * db에 복호화된 password와 유저가 입력한 password를 복호화한 값이 같은지 비교
     * 로그인 성공시 20분의 유지시간을 가진 세션 부여
     * @param memberId 사용자가 입력한 아이디
     * @param password 사용자가 입력한 비밀번호
     */
    public Object memberLogin(String memberId, String password, HttpSession httpSession) {
//        String encodedPassword = userDao.getEncodedPassword(userId);
//        boolean matches = passwordEncoder.matches(password, encodedPassword);
//        if (!matches) {
//            throw new RuntimeException("등록되지 않은 유저입니다.");
//        }
//        UserDto userDto = userDao.userLogin(userId);
//        httpSession.setAttribute("UserInfo", userDto);
//        httpSession.setMaxInactiveInterval(20*60);
//        return userDto;
        return "";
    }
}
