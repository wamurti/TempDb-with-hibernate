package DbTemp.Controllers;
import DbTemp.Models.Ackesensor;
import DbTemp.Models.Bjornsensor;
import DbTemp.Models.Jonassensor;
import DbTemp.Models.frejsensor;
import DbTemp.Repos.AckeRepo;
import DbTemp.Repos.BjornRepo;
import DbTemp.Repos.FrejRepo;
import DbTemp.Repos.JonasRepo;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    // Retur on name, klicka på knappen
    @RequestMapping("/")
    public String chooseByName() {
        return "vem";
    }

    @PostMapping("/frej")
    public String getFrejsSensor(Model model) {
        List<frejsensor> t = frejRepo.findAll();
        model.addAttribute("allTemps", t);
        model.addAttribute("Namn","Frej");
        return "namnretur";
    }
    @PostMapping("/bjorn")
    public String getbjornsSensor(Model model) {
        List<Bjornsensor> t = bjornRepo.findAll();
        model.addAttribute("allTemps", t);
        model.addAttribute("Namn","Björn");
        return "namnretur";
    }
    @PostMapping("/jonas")
    public String getJonasSensor(Model model) {
        List<Jonassensor> t = jonasRepo.findAll();
        model.addAttribute("allTemps", t);
        model.addAttribute("Namn","Jonas");
        return "namnretur";
    }
    @PostMapping("/acke")
    public String getAckesSensor(Model model) {
        List<Ackesensor> t = ackeRepo.findAll();
        model.addAttribute("allTemps", t);
        model.addAttribute("Namn", "Acke");
        return "namnretur";
    }

    @RequestMapping("/senaste") /* Kanske borde kasta in tidsfunktionerna i Repos */
    public String getSenasteTemp(Model model) {
        List<frejsensor> f = frejRepo.findAll();
        List<Jonassensor> j = jonasRepo.findAll();
        List<Bjornsensor> b = bjornRepo.findAll();
        List<Ackesensor> a = ackeRepo.findAll();

        Comparator<frejsensor> tidfrej = Comparator.comparing(frejsensor::getTid);
        frejsensor fs = f.stream().max(tidfrej).get();
        Comparator<Jonassensor> tidjonas = Comparator.comparing(Jonassensor::getTid);
        Jonassensor js = j.stream().max(tidjonas).get();
        Comparator<Bjornsensor> tidbjorn = Comparator.comparing(Bjornsensor::getTid);
        Bjornsensor bs = b.stream().max(tidbjorn).get();
        Comparator<Ackesensor> tidacke = Comparator.comparing(Ackesensor::getTid);
        Ackesensor as = a.stream().max(tidacke).get();


        model.addAttribute("fs", fs);
        model.addAttribute("js", js);
        model.addAttribute("bs", bs);
        model.addAttribute("as", as);
        model.addAttribute("Title", "Alla Sensorers Senaste");
        return "allassenaste";
    }

    @RequestMapping("/varmaste")
    public String getVarmasteTemp(Model model) {
        Comparator<frejsensor> varmfrej = Comparator.comparing(frejsensor::getTemperatur).reversed();
        List<frejsensor> fv = frejRepo.findAll().stream().sorted(varmfrej).limit(5).toList();

        Comparator<Bjornsensor> varmbjorn = Comparator.comparing(Bjornsensor::getTemperatur).reversed();
        List<Bjornsensor> bv = bjornRepo.findAll().stream().sorted(varmbjorn).limit(5).toList();

        Comparator<Jonassensor> varmjonas = Comparator.comparing(Jonassensor::getTemperatur).reversed();
        List<Jonassensor> jv = jonasRepo.findAll().stream().sorted(varmjonas).limit(5).toList();

        Comparator<Ackesensor> varmacke = Comparator.comparing(Ackesensor::getTemperatur).reversed();
        List<Ackesensor> av = ackeRepo.findAll().stream().sorted(varmacke).limit(5).toList();

        model.addAttribute("fv",fv);
        model.addAttribute("bv",bv);
        model.addAttribute("jv",jv);
        model.addAttribute("av",av);

        model.addAttribute("Title", "Topp 5 Högsta Temp");
        return "allasvarmaste";
    }
    @RequestMapping("/kallaste")
    public String getKallasteTemp(Model model) {
        Comparator<frejsensor> kallfrej = Comparator.comparing(frejsensor::getTemperatur);
        List<frejsensor> fv = frejRepo.findAll().stream().sorted(kallfrej).limit(5).toList();

        Comparator<Bjornsensor> kallbjorn = Comparator.comparing(Bjornsensor::getTemperatur);
        List<Bjornsensor> bv = bjornRepo.findAll().stream().sorted(kallbjorn).limit(5).toList();

        Comparator<Jonassensor> kalljonas = Comparator.comparing(Jonassensor::getTemperatur);
        List<Jonassensor> jv = jonasRepo.findAll().stream().sorted(kalljonas).limit(5).toList();

        Comparator<Ackesensor> varmacke = Comparator.comparing(Ackesensor::getTemperatur);
        List<Ackesensor> av = ackeRepo.findAll().stream().sorted(varmacke).limit(5).toList();

        model.addAttribute("fv",fv);
        model.addAttribute("bv",bv);
        model.addAttribute("jv",jv);
        model.addAttribute("av",av);

        model.addAttribute("Title", "Topp 5 Lägsta Temp");
        return "allasvarmaste";
    }

}
