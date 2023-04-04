package dao;

import dto.ChatDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ChatDAO {
    List<ChatDTO> selectAll();

    Optional<ChatDTO> selectOne(long id);

    String create(ChatDTO dto);

    String update(ChatDTO dto);

    String delete(long id);



}
