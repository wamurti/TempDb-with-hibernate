# MariaThyme
### Multiple remote sensor monitoring through Hibernate backend &amp; Thymeleaf frontend


This was a group-project at Nackademin to learn more about System Integration.
We were four in the group and each one of us used either a Raspberry pi or an arduino
to setup a temperature sensor and send the data to a remote Mariadb-database.
The data from that database was then retrived and handled by a spring boot/Hibernate program
and presented with html/thymeleaf templates in a browser.

<p><br></p>

## The Setup

![setup](/arkitektur.png)

<p><br></p>

#### With Spring boot/Hibernate you need to create and map models to tables in the database with the same name and attributes:
```
@Data
@Entity
@Table(name="frejsensor")
@NoArgsConstructor
@AllArgsConstructor
public class frejsensor {
    @Id
    @GeneratedValue
    private Long id;
    private float temperatur;
    @CreationTimestamp
    private LocalDateTime tid;
```
<p><br></p>

#### You then create an interface with JpaRepositories to get generated repository implemenations, such as getTemperatureById()
```
public interface FrejRepo extends JpaRepository<frejsensor,Long> {}
```
<p><br></p>

#### This make building a TempController very easy. 
#### Simply pass the repos to the Controller and you have all basic methods ready.

```
@Controller
@RequestMapping(path = "/temps")
public class TempController {
    private final FrejRepo frejRepo;
    private final JonasRepo jonasRepo;
    private final BjornRepo bjornRepo;
    private final AckeRepo ackeRepo;

    public TempController(FrejRepo frejRepo, JonasRepo jonasRepo, BjornRepo bjornRepo, AckeRepo ackeRepo) {
        this.frejRepo = frejRepo;
        this.jonasRepo = jonasRepo;
        this.bjornRepo = bjornRepo;
        this.ackeRepo = ackeRepo;
    }

    @RequestMapping("/totalen")
    public String getAllaTemp(Model model) {
        List<frejsensor> f = frejRepo.findAll();
        List<Jonassensor> j = jonasRepo.findAll();
        List<Bjornsensor> b = bjornRepo.findAll();
        List<Ackesensor> a = ackeRepo.findAll();

        model.addAttribute("acketemps", a);
        model.addAttribute("frejtemps", f);
        model.addAttribute("jonastemps", j);
        model.addAttribute("bjorntemps", b);
        model.addAttribute("Title", "Alla Sensorer");
        return "allastemp";
    }
```

<p><br></p>

### The frontend will display several buttons representing functions 

![frontend](/frontend.png)

#### Functions included in TempController
* Show Everyones Data
* Show individual Data
* Find/show Latest
* Find/Show top-5 Highest Temperature
* Find/Show top-5 Lowest Temperature


## Thanks to the rest of the team: Jonas, Björn and Alexander
