package kovacseni.zooadoption;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {
    private Zoo zoo;

    @BeforeEach
    public void setUp() {
        try {
            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("12345");

            Flyway fw = Flyway.configure().dataSource(dataSource).load();
            fw.clean();
            fw.migrate();

            zoo = new Zoo();
            zoo.loadAdoptiveParentsFromDatabase(dataSource);

        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not get data", sqle);
        }
    }

    @Test
    public void testGetTextsOfLetters() {
        List<String> textsOfLetters = zoo.getTextsOfLetters("conferenceforadoptiveparents.txt");

        Assertions.assertEquals("""
                Kedves My Children!

                Nagy szeretettel meghívjuk Önt Állatkertünkbe, az állatainkat
                örökbefogadó szülők első Szülői Értekezletére, melynek dátuma:
                2021-10-04, időpontja: 17:00.
                A szülői értekezleten elmeséljük, hogyan viseli magát mostanság
                az Ön által örökbefogadott Koala, ezáltal Ön is
                egy kicsit betekintést nyerhet a nálunk folyó munkába.

                Örömmel tekintünk a közeli találkozás elé!
                Üdvözlettel: Állatkert""", textsOfLetters.get(12));
    }

    @Test
    public void testGetFirstAdoption() {
        Adoption adoption = new Adoption("Galapagos dove", LocalDate.of(2021, 3, 4));
        Assertions.assertEquals(adoption.getAnimal(), zoo.getFirstAdoption().getAnimal());
        Assertions.assertEquals(adoption.getAdoptionDate(), zoo.getFirstAdoption().getAdoptionDate());
    }

    @Test
    public void testGetAnimalNamesReverseOrdered() {
        List<String> animalNamesReverseOrdered = zoo.getAnimalNamesReverseOrdered();
        Assertions.assertEquals("Spotted deer", animalNamesReverseOrdered.get(3));
        Assertions.assertEquals("Egyptian viper", animalNamesReverseOrdered.get(11));
    }

    @Test
    public void testGetExactPerson() {
        Assertions.assertEquals("Reginald Trim", zoo.getExactPerson("rtrim4@comcast.net").getName());
    }

    @Test
    public void testGetExactPersonNoSuchPerson() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> zoo.getExactPerson("kiss.jozsef@gmail.com"));
        Assertions.assertEquals("No such person among the adoptive parents!", ex.getMessage());
    }
}