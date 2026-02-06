package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private long recordCount;
    private T data;
    private int status;
    private String message;
}