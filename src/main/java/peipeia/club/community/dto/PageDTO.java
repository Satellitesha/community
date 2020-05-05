package peipeia.club.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Data
@Component
public class PageDTO {
    private List<QuestionDTO> question;
    private boolean showPrevious;
    private  boolean showFirstPage;
    private  boolean showNextPage;
    private  boolean showEndPage;
    private  Integer page;
    private  List<Integer> pages= new ArrayList<>();
    private  Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.page=page;
        this.totalPage=totalPage;
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page-i);
            }else if (page+i<=totalPage){
                pages.add(page+i);
            }
        }
        // 是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        // 是否展示下一页
        if (page == totalPage) {
            showNextPage = false;
        } else {
            showNextPage = true;
        }

        // 是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // 是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
