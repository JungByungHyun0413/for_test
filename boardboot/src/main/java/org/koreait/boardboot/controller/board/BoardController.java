package org.koreait.boardboot.controller.board;

import jakarta.validation.Valid;
import org.koreait.boardboot.models.board.Board;
import org.koreait.boardboot.models.board.BoardDao;
import org.koreait.boardboot.models.board.BoardListService;
import org.koreait.boardboot.models.board.BoardSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardSaveService saveService;
    @Autowired
    private BoardListService listService;
    @Autowired
    private BoardDao boardDao;

    @GetMapping("/write")
    public String write(@ModelAttribute BoardForm boardForm) {

        return "board/write";
    }

    @PostMapping("/save")
    public String save(@Valid BoardForm boardForm, Errors errors) {
        if (errors.hasErrors()) {
            return "board/write";
        }

        // 저장처리 후 리스트 페이지로
        saveService.save(boardForm);

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> items = listService.gets();
        model.addAttribute("items", items);

        return "board/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Board view = boardDao.get(id);
        model.addAttribute("view", view);

        return "board/view";
    }
}
