package kr.app.darthvader.domain.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMapResult<T> {

    private T result;

}