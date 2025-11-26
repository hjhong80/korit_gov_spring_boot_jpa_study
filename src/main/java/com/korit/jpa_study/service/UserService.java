package com.korit.jpa_study.service;


import com.korit.jpa_study.dto.AddUserReqDto;
import com.korit.jpa_study.dto.ApiRespDto;
import com.korit.jpa_study.dto.EditPasswordDto;
import com.korit.jpa_study.dto.EditUsernameDto;
import com.korit.jpa_study.entity.User;
import com.korit.jpa_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<?> addUser(AddUserReqDto addUserReqDto) {
        Optional<User> foundUser = userRepository.findByUsername(addUserReqDto.getUsername());
        if (foundUser.isPresent()) {
            System.out.println(">>>>> 추가 : 이름 중복 <<<<<");
            return new ApiRespDto<>("failed", "중복된 username 입니다.",addUserReqDto.getUsername());
        }
        userRepository.save(addUserReqDto.toEntity());
        return new ApiRespDto<>("success","가입을 환영합니다.",addUserReqDto.getUsername() + " 님");
    }

    public ApiRespDto<?> getAllUser() {
        List<User> foundUser = userRepository.findAll();
        if (foundUser.isEmpty()) {
            System.out.println(">>>>> 전체검색 : 목록 없음 <<<<<");
            return new ApiRespDto<>("failed", "목록이 없습니다.",null);
        }
        return new ApiRespDto<>("success","조회에 성공하였습니다.",foundUser);
    }

    public ApiRespDto<?> getUserByUserId(Integer userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            System.out.println(">>>>> 단건검색 : userId 없음 <<<<<");
            return new ApiRespDto<>("failed", "존재하지 않는 userId 입니다.",userId);
        }
        return new ApiRespDto<>("success","조회에 성공하였습니다.",foundUser.get());
    }

    public ApiRespDto<?> getUserByUsername(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isEmpty()) {
            System.out.println(">>>>> 단건검색 : username 없음 <<<<<");
            return new ApiRespDto<>("failed", "존재하지 않는 username 입니다.",username);
        }
        return new ApiRespDto<>("success","조회에 성공하였습니다.",foundUser.get());
    }

    public ApiRespDto<?> editUsername (EditUsernameDto editUsernameDto) {
        Optional<User> foundUser = userRepository.findById(editUsernameDto.getUserId());
        if (foundUser.isEmpty()) {
            System.out.println(">>>>> 수정 : userId 없음 <<<<<");
            return new ApiRespDto<>("failed", "존재하지 않는 userId 입니다.",editUsernameDto.getUserId());
        }
        User userToEdit = foundUser.get();
        userToEdit.setUsername(editUsernameDto.getUsername());
        userToEdit.setUpdateDt(LocalDateTime.now());
        userRepository.save(userToEdit);
        return new ApiRespDto<>("success","수정에 성공하였습니다.",editUsernameDto);
    }

    public ApiRespDto<?> editPassword (EditPasswordDto editPasswordDto) {
        Optional<User> foundUser = userRepository.findById(editPasswordDto.getUserId());
        if (foundUser.isEmpty()) {
            System.out.println(">>>>> 수정 : userId 없음 <<<<<");
            return new ApiRespDto<>("failed", "존재하지 않는 userId 입니다.",editPasswordDto.getUserId());
        }
        User userToEdit = foundUser.get();
        userToEdit.setPassword(editPasswordDto.getPassword());
        userToEdit.setUpdateDt(LocalDateTime.now());
        userRepository.save(userToEdit);
        return new ApiRespDto<>("success","수정에 성공하였습니다.",null);
    }

    public ApiRespDto<?> removeUser(Integer userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            System.out.println(">>>>> 탈퇴 : userId 없음 <<<<<");
            return new ApiRespDto<>("failed", "존재하지 않는 userId 입니다.",userId);
        }
        User userToRemove = foundUser.get();
        userRepository.delete(userToRemove);
        return new ApiRespDto<>("success","탈퇴에 성공하였습니다.",userToRemove.getUsername() + " 님");
    }
}
