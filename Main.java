package Homework_Population_Census;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Collection<Person> persons = new ArrayList<>();

        addPersons(persons);
        countUnderAge(persons);
        recruitsList(persons);
        employableList(persons);
    }

    public static void addPersons(Collection<Person> persons){
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        for(int i = 0; i < 10_000_000; i++){
            persons.add(new Person(
                            names.get(new Random().nextInt(names.size())),
                            families.get(new Random().nextInt(families.size())),
                            new Random().nextInt(100),
                            Sex.values()[new Random().nextInt(Sex.values().length)],
                            Education.values()[new Random().nextInt(Education.values().length)]
                    )
            );
        }
    }

    public static void countUnderAge(Collection<Person> persons){
        System.out.println("Количество несовершеннолетних");
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);
    }

    public static void recruitsList(Collection<Person> persons){
        System.out.println("Список призывников");
        List<String> recruitList = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(recruitList);
    }

    public static void employableList(Collection<Person> persons){
        System.out.println("Список трудоспособных людей");
        List<Person> employableList = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 60 && person.getSex().equals(Sex.WOMEN)
                        || person.getAge() >= 18 && person.getAge() <= 65 && person.getSex().equals(Sex.MEN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        for (Person person : employableList){
            System.out.println(person);
        }
    }
}
