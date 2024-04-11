import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minorList = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();

        System.out.println("Колличество несовершеннолетних" + " " + minorList);

        List militaryAccount = persons.stream()
                .filter(age -> age.getAge() > 18 && age.getAge() < 27)
                .filter(sex -> sex.getSex() == Sex.MAN)
                .map(f -> f.getFamily())
                .collect(Collectors.toList());

        System.out.println("Список мужчин призывного возраста" + " " + militaryAccount);


        List ableBodiedWoman = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(age -> age.getAge() > 18 && age.getAge() < 60)
                .filter(sex -> sex.getSex() == Sex.WOMAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Список трудоспособных женщин с в/о" + " " + ableBodiedWoman);


        List ableBodiedMan = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(age -> age.getAge() > 18 && age.getAge() < 65)
                .filter(sex -> sex.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Список трудоспособных мужчин с в/о" + " " + ableBodiedMan);
    }
}

