import java.util.List;
import java.util.UUID;

public class Customer {

    private UUID id;
    private String username;
    private String email;
    private List<Address> address; //requirement - can have more than one address
    // polymorphism always preferred --> Interface or Parent Class variableName = new Class ==> List<Address> address= new ArrayList<>();

    public Customer(UUID id, String username, String email) { // 2 constructor. this is with username and email
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Customer(UUID id, String username, String email, List<Address> address) {// this constructor with multiple addresses from the requirement. So customer can register in 2 different option.
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<Address> getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
