package lab.edu.graphql.input;

public record NewPatient(
        String firstName,
        String lastName,
        int age,
        NewAddress primaryAddress
) {
}
