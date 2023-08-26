package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.ChatLieu;
import edu.poly.shopbangiay.repository.ChatLieuRepository;
import edu.poly.shopbangiay.service.ChatLieuService;

import java.util.List;

public class ChatLieuServiceImpl implements ChatLieuService {
    ChatLieuRepository chatLieuRepository = new ChatLieuRepository();

    @Override
    public List<ChatLieu> getList() {
        return chatLieuRepository.getList();
    }

    @Override
    public ChatLieu getCLByMa(String ma) {
        return chatLieuRepository.getCLByMa(ma);
    }


    @Override
    public List<ChatLieu> timKiem(String ten) {
        return chatLieuRepository.timKiem(ten);
    }

    @Override
    public Boolean them(ChatLieu chatLieu) {
        return chatLieuRepository.them(chatLieu);
    }

    @Override
    public Boolean sua(ChatLieu chatLieu) {
        return chatLieuRepository.sua(chatLieu);
    }

    @Override
    public Boolean xoa(ChatLieu chatLieu) {
        return chatLieuRepository.xoa(chatLieu);
    }
}
