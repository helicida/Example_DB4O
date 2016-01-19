package Examples;

/**
 * Created by 46465442z on 19/01/16.
 */
public class Person {

        public Person(){ }

        public Person(String firstName, String lastName, int age){
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public String getFirstName() { return firstName; }
        public void setFirstName(String value) { firstName = value; }

        public String getLastName() { return lastName; }
        public void setLastName(String value) { lastName = value; }

        public int getAge() { return age; }
        public void setAge(int value) { age = value; }

        public String toString(){
            return
                    "[Person: \n" +
                            "firstName = " + firstName + " \n" +
                            "lastName = " + lastName + " \n" +
                            "age = " + age + " \n" +
                            "]";
        }

        public boolean equals(Object rhs)
        {
            if (rhs == this){
                return true;
            }


            if (!(rhs instanceof Person)){
                return false;
            }


            Person other = (Person)rhs;
            return (this.firstName.equals(other.firstName) &&
                    this.lastName.equals(other.lastName) &&
                    this.age == other.age);
        }

        private String firstName;
        private String lastName;
        private int age;
}


