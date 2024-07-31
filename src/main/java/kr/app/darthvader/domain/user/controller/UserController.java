package kr.app.darthvader.domain.user.controller;

import jakarta.validation.Valid;
import kr.app.darthvader.domain.common.model.dto.RequestResult;
import kr.app.darthvader.domain.common.model.dto.ResponseMapResult;
import kr.app.darthvader.domain.user.model.dto.request.TuserRequestDto;
import kr.app.darthvader.domain.user.model.dto.response.TuserResponseDto;
import kr.app.darthvader.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public RequestResult<TuserResponseDto> saveUser(@RequestBody @Valid TuserRequestDto dto) {
        return new RequestResult<>(userService.saveUser(dto));
    }

    @GetMapping("/admin/user/{id}")
    public ResponseMapResult<TuserResponseDto> selectUserById(@PathVariable("id") String userId) {
        return new ResponseMapResult<>(userService.selectUserById(userId));
    }


}
