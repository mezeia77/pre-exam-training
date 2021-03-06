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

//Sz??l??i ??rtekezlet
//A m??ltkori ??llatkert az??ta egy kicsit kib??v??lt, ??s most m??r sokkal t??bbf??le ??llatnak ad otthont.
// Ez??rt elind??tott??k az ??r??kbefogad??si programjukat, amelynek keret??ben b??rki egy jelk??pes ??sszeg??rt egy ??ltala szabadon v??lasztott ??llat jelk??pes ??r??kbefogad?? sz??l??j??v?? v??lhat. Van is m??r n??h??ny lelkes jelentkez??, akiket egy adatb??zisban t??rolnak (n??v, e-mail c??m, ??r??kbefogadott ??llat ??s ??r??kbefogad??s d??tuma adatokkal).
// Az ??llatkert a kapott t??mogat??s??rt cser??be ??vi egyszer egy sz??l??i ??rtekezletet tart az ??r??kbefogad??knak, amikor azok arra a napra ingyen bel??p??t kapnak ??s m??g egy besz??mol??t is meghallgathatnak kedvenc??k viselt dolgair??l.
// (Nem vicc, ilyet t??nyleg csin??lnak, pl. a budapesti ??llatkertben. :) )
// Erre az alkalomra k??l??n megh??v??t is kik??ldenek minden ??rintettnek. Az esem??ny st??lusosan az ??llatok Vil??gnapj??n, 2021. okt??ber 4-??n lesz, d??lut??n 5-kor.
//
//A te feladatod az ??sszes ilyen megh??v??lev??l sz??veg??nek a legener??l??sa a megadott template f??jl alapj??n (src/main/resources/conferenceforadoptiveparents.txt).
// A met??dus neve: getTextsOfLetters(String filename). Fontos, hogy ez ??gy val??suljon meg, hogy ha k??s??bb beker??lnek m??g ??jabb t??mogat??k az adatb??zisba,
// akkor a k??don ne kelljen semmit m??dos??tani.
//
//Ezenk??v??l (csak ??gy, gyakorl??sk??nt) implement??ld a k??vetkez?? met??dusokat is:
//
//getFirstAdoption() : visszaadja az els?? ??r??kbefogad??st (??llat, d??tum).
//getAnimalNamesReverseOrdered() : visszaadja egy adatszerkezetben az ??sszes, eddig ??r??kbefogadott ??llat nev??t, ford??tott ABC-sorrendben.
//getExactPerson(String email) : megkeres egy konkr??t ??r??kbefogad?? sz??l??t az e-mail c??me alapj??n.
