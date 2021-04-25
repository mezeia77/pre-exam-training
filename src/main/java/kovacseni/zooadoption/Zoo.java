package kovacseni.zooadoption;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Zoo {

    public Zoo() {
    }

    List<Adoption> adoptions = new ArrayList<>();
    List<Person> personList = new ArrayList<>();

    private final String DATE = "2021-10-04";
    private final String TIME = "17:00";

    public void loadAdoptiveParentsFromDatabase(MariaDbDataSource dataSource) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from zooadoptions")
        ){
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    String name = rs.getString("person_name");
                    String email = rs.getString("email");
                    String animal = rs.getString("animal");
                    LocalDate adoption_date = rs.getDate("adoption_date").toLocalDate();
                    personList.add(new Person(name, email));
                    adoptions.add(new Adoption(animal, adoption_date));
                }
            } catch (SQLException sqlException) {
                throw new IllegalStateException("Cannot execute", sqlException);
            }
        } catch (SQLException sqlException) {
            throw new IllegalStateException("No data found", sqlException);
        }
    }

    public Adoption getFirstAdoption() {
        Adoption result = null;
        LocalDate firstAdoptionDate = LocalDate.MAX;
        for (Adoption ad:adoptions){
            if(ad.getAdoptionDate().isBefore(firstAdoptionDate)){
                firstAdoptionDate = ad.getAdoptionDate();
                result = ad;
            }
        }
        return result;
    }

    public List<String> getAnimalNamesReverseOrdered() {
        List<String> result = new ArrayList<>();
        for(Adoption ad:adoptions){
            result.add(ad.getAnimal());
        }
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }

    public Person getExactPerson(String email) {
        Person result = null;
        for(Person p:personList){
            if(p.getEmail().equals(email)){
                result = p;
                break;
            }
        }
        if(result==null){
            throw new IllegalArgumentException("No such person among the adoptive parents!");
        }
        return result;
    }

    public List<String> getTextsOfLetters(String filename) {
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = Files.newBufferedReader(Path.of("src//main//resources//zooadoption//" + filename));
            String line;
            while ((line= br.readLine())!=null){
                text.append(line + "\n");
            }
        } catch (IOException e) {
            throw new IllegalStateException("File not found", e);
        }
        text = text.deleteCharAt(text.length()-1);    //remove last "\n"
        List<String> result = new ArrayList<>();

        for(int i = 0; i< personList.size(); i++){
            String temp = text.toString();
            temp = temp.replace("{datum}", DATE);
            temp = temp.replace("{idopont}", TIME);
            temp = temp.replace("{orokbefogado_szulo}", personList.get(i).getName());
            temp = temp.replace("{orokbefogadott_allat}", adoptions.get(i).getAnimal());
            result.add(temp);
        }
        return result;
    }
}

//Szülői értekezlet
//A múltkori állatkert azóta egy kicsit kibővült, és most már sokkal többféle állatnak ad otthont.
// Ezért elindították az örökbefogadási programjukat, amelynek keretében bárki egy jelképes összegért egy általa szabadon választott állat jelképes örökbefogadó szülőjévé válhat. Van is már néhány lelkes jelentkező, akiket egy adatbázisban tárolnak (név, e-mail cím, örökbefogadott állat és örökbefogadás dátuma adatokkal).
// Az állatkert a kapott támogatásért cserébe évi egyszer egy szülői értekezletet tart az örökbefogadóknak, amikor azok arra a napra ingyen belépőt kapnak és még egy beszámolót is meghallgathatnak kedvencük viselt dolgairól.
// (Nem vicc, ilyet tényleg csinálnak, pl. a budapesti Állatkertben. :) )
// Erre az alkalomra külön meghívót is kiküldenek minden érintettnek. Az esemény stílusosan az Állatok Világnapján, 2021. október 4-én lesz, délután 5-kor.
//
//A te feladatod az összes ilyen meghívólevél szövegének a legenerálása a megadott template fájl alapján (src/main/resources/conferenceforadoptiveparents.txt).
// A metódus neve: getTextsOfLetters(String filename). Fontos, hogy ez úgy valósuljon meg, hogy ha később bekerülnek még újabb támogatók az adatbázisba,
// akkor a kódon ne kelljen semmit módosítani.
//
//Ezenkívül (csak úgy, gyakorlásként) implementáld a következő metódusokat is:
//
//getFirstAdoption() : visszaadja az első örökbefogadást (állat, dátum).
//getAnimalNamesReverseOrdered() : visszaadja egy adatszerkezetben az összes, eddig örökbefogadott állat nevét, fordított ABC-sorrendben.
//getExactPerson(String email) : megkeres egy konkrét örökbefogadó szülőt az e-mail címe alapján.
