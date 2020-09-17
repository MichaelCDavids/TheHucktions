package net.hackathon;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManagementQueriesTest {

    Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost/hucktion_db?user=mike&password=mike123");
    @BeforeEach
    void beforeEach() {
        jdbi.withHandle(h -> {
            h.execute("delete from users");
            return null;
        });
    }


}
