package org.koreait.boardboot.models.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardListService {
    @Autowired
    private BoardDao boardDao;

    public List<Board> gets() {
        return boardDao.gets();
    }
}
