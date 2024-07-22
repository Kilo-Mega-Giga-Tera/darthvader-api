package kr.app.darthvader.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserMessageException extends RuntimeException {

    private String message;

}
