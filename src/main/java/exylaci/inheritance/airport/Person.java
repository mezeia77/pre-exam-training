package exylaci.inheritance.airport;

public abstract class Person {
    private String name;
    private int age;
    private Position position;
    private String seat;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Position getPosition() {
        return position;
    }

    public String getSeat() {
        return seat;
    }

    public abstract boolean isTravel();

    public void increaseAge(){
            age += 1;
    }
}



//Egy repülőgépen mindenféle ember megfordul. Utas, sztyui, pilóta, másodtiszt, rádiós, takarító, szerelő, vagy más egyéb földi kiszolgáló személyzet. (Szerencsére gépeltérítő nincs köztük.)
//Mindenkinek van neve, életkora. (Kivéve a sztyuikat, mert hölgytől ilyet nem illik kérdezni, ezért náluk nem mindig tudjuk.)
//Ezen felül: pilótáknak és a sztyuiknak beosztása, a földi kiszolgáló személyzetnek munkaköre, az utasoknak ülőhelye van.
//
//A Person osztályt nem lehet példányosítani!
//
//-Gyüjtsd ki a sztyuikat!
//-A magasságtól megszédültek az utasok. Minden ablak mellett ülő cseréljen helyet a középen ülővel. (A-C, D-F székek)
//-Ki volt a legidősebb ember, aki járt ugyan a gépen, de nem szállt fel vele?
//-Ki volt a legfiatalabb, akivel felszállt a gép?
//-Ki szállt fel a géppel?
//-Készíts utaslistát az ülőhelyek szerint!
//-Beköszöntött az új esztendő. Mindenki egy évvel idősebb lett.
//-Felszállás előtt a földi kiszolgáló személyzet ki kell szálljon a gépből.
//-Bónusz kérdés: hány éves a kapitány? :)