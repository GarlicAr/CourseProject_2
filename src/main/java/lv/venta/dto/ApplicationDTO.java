package lv.venta.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationDTO {

    @NotNull
    @Size(min = 3, max = 20)
    private String title;

    @NotNull
    @Size(min = 3)
    private String text;

}
