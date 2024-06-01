package net.ent.etnc.dino.controller;

import net.ent.etnc.dino.model.entity.Dino;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class DinoController {

    private List<Dino> dinos = new ArrayList<>();

    @GetMapping("/dino/list")
    public String listDino(Model model) {
        model.addAttribute("dinos", dinos);
        return "dino/list";
    }

    @GetMapping("/dino/add")
    public String showAddDinoForm(Model model) {
        model.addAttribute("dino", new Dino());
        return "dino/add";
    }


    @PostMapping("/dino/add")
    public String addDino(Dino dino, RedirectAttributes redirectAttributes) {
        dino.setId(UUID.randomUUID());
        dinos.add(dino);
        redirectAttributes.addFlashAttribute("message", "Dino ajouté");
        return "redirect:/dino/list";
    }

    @PostMapping("/dino/delete/{id}")
    public String deleteDino(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        dinos.removeIf(dino -> dino.getId().equals(id));
        redirectAttributes.addFlashAttribute("message", "Dino Supprimé !!!");
        return "redirect:/dino/list";
    }

    @GetMapping("/dino/update/{id}")
    public String showEditDinoForm(@PathVariable UUID id, Model model) {
        Dino dinoToEdit = dinos.stream()
                .filter(dino -> dino.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (dinoToEdit == null) {
            return "redirect:/dino/list";
        }
        model.addAttribute("dino", dinoToEdit);
        return "dino/edit";
    }

    @PostMapping("/dino/update/{id}")
    public String editDino(@PathVariable UUID id, Dino dino, RedirectAttributes redirectAttributes) {
        int dinoIndex = -1;
        for (int i = 0; i < dinos.size(); i++) {
            if (dinos.get(i).getId().equals(id)) {
                dinoIndex = i;
                break;
            }
        }
        if (dinoIndex != -1) {
            dino.setId(id);
            dinos.set(dinoIndex, dino);
            redirectAttributes.addFlashAttribute("message", "Dino modifié");
        }
        return "redirect:/dino/list";
    }

}
