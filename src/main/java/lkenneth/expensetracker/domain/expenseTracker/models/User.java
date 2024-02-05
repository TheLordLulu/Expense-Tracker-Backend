package lkenneth.expensetracker.domain.expenseTracker.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@RequiredArgsConstructor
@Document()
public class User {

    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;



    @DBRef
    private List<Income> incomes = new ArrayList<>();

    @DBRef
    private List<Expense> expenses = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", incomes=" + incomes +
                ", expenses=" + expenses +
                '}';
    }
}
