package org.example.mvcthylemeafhopital.web;


import lombok.AllArgsConstructor;
import org.example.mvcthylemeafhopital.entities.Patient;
import org.example.mvcthylemeafhopital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword)
                        {

        //Page<Patient> patientsPage = patientRepository.findAll(PageRequest.of(page,size));
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
       // model.addAttribute("keyword", keyword);
        return "patients";
    }
    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);

        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @PostMapping(path = "/save")
    public String save(@Valid Patient patient, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page){

        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient Introuvable");
        model.addAttribute(patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";

    };
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

}
