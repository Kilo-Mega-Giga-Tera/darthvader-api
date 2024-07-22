package kr.app.darthvader.domain.user.controller;

import jakarta.validation.Valid;
import kr.app.darthvader.domain.common.model.dto.RequestResult;
import kr.app.darthvader.domain.common.model.dto.ResponseListResult;
import kr.app.darthvader.domain.common.model.dto.ResponseMapResult;
import kr.app.darthvader.domain.user.model.dto.TuserRequestDto;
import kr.app.darthvader.domain.user.model.dto.TuserResponseDto;
import kr.app.darthvader.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public RequestResult<TuserResponseDto> user(@RequestBody @Valid TuserRequestDto dto) throws Exception {
        return new RequestResult<>(userService.saveUser(dto));
    }

    @GetMapping("/user")
    public ResponseListResult<List<TuserResponseDto>> user() {
        List<TuserResponseDto> tuserResponseDto = userService.selectUser();
        return new ResponseListResult<>(tuserResponseDto, tuserResponseDto.size());
    }

    @GetMapping("/user/{id}")
    public ResponseMapResult<TuserResponseDto> userById(@PathVariable("id") String userId) {
        return new ResponseMapResult<>(userService.selectUserById(userId));
    }


}
