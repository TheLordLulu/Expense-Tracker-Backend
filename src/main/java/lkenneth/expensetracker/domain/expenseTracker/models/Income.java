package lkenneth.expensetracker.domain.expenseTracker.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@NoArgsConstructor
@Data
@RequiredArgsConstructor
@Document()
public class Income {

    @Id
    private String id;

    @NonNull
    private String title;
    @NonNull
    private double amount;
    @NonNull
    private String type;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date date;
    @NonNull
    private String category;
    @NonNull
    private String description;

    @DBRef
    private User user;

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
