package exylaci.inheritance.airport;

import java.util.*;

public class Plane {

    private List<Person> people = new ArrayList<>();

    public Plane(List<Person> people) {
        if(people==null){
            throw new IllegalArgumentException("Person list is a must!");
        }
        this.people = people;
    }

    public Set<Person> getCabinCrew() {
        Set<Person> result = new HashSet<>();
        for (Person p:people){
            if(p.getPosition()!=null){
                result.add(p);
            }
        }
        System.out.println(result);
        return result;
    }

    public String findTheOldest() {
        if(people.size()==0){
            throw new IllegalStateException("The plane is empty!");
        }
        int maxAge = 0;
        String winner="";
        for (Person p:people){
            if(p.getAge()>maxAge){
                winner= p.getName();
                maxAge=p.getAge();
            }
        }
        return winner;
    }

    public String findTheYoungest() {
        if(people.size()==0){
            throw new IllegalStateException("The plane is empty!");
        }
        int maxAge = 150;
        String winner="";
        for (Person p:people){
            if(p.getAge()>0 && p.getAge()<maxAge){
                winner= p.getName();
                maxAge=p.getAge();
            }
        }
        return winner;
    }

    public Person findTheCaptain() {
        Person captain = null;
        if(people.size()==0){
            throw new IllegalStateException("The plane is empty!");
        }
        
        for (Person p:people){
            if(p.getPosition()==Position.CAPTAIN){
                captain=p;
            }
        }
        return captain;
    }

    public int howOldTheCaptain() {
        int age = 0;
        for (Person p:people){
            if(p.getPosition()==Position.CAPTAIN){
                age = p.getAge();
                break;
            }
        }
        return age;
    }

    public Map<String, Person> getPassengerList() {
        Map<String, Person> result = new HashMap<>();
        for (Person p:people){
            if(p.getSeat()!=null){
                result.put(p.getSeat(), p);
            }
        }
        return result;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void newYearsEve() {
        List<Person> temp = new ArrayList<>(people);
        Iterator<Person> it = temp.iterator();
        while (it.hasNext()){
            Person p = it.next();
            p.increaseAge();
        }
        people = temp;
    }

    public void beforeTakeOff() {
        List<Person> temp = new ArrayList<>(people);
        Iterator<Person> it = temp.iterator();
        while (it.hasNext()){
            Person p = it.next();
            if(!p.isTravel()){
                it.remove();
            }
        }
        people= temp;
    }

}
