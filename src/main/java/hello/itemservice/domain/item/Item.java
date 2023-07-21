package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang="javascript", script="_this.price * _this.quantity >= 10000", message = "값이 10000 이하여야합니다.")
//object validation의 경우에는 다음과 같이 ScriptAssert를 사용하나 제약조건도 많고 지저분해진다.
//그냥 기존에 resultBinding처럼 해결하는게 낫다.
public class Item {

    private Long id;
    @NotBlank(message = "공백x")
    private String itemName;
    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;
    @NotNull
    @Max(999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
