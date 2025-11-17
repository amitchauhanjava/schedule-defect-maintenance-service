package in.org.cris.cmm.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private long recordCount;
    private T data;
    private int status;
    private String message;
}