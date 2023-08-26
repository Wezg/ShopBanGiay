package edu.poly.shopbangiay.service;

import edu.poly.shopbangiay.model.ChatLieu;

import java.util.List;

public interface ChatLieuService {
    List<ChatLieu> getList();
    ChatLieu getCLByMa(String ma);
    List<ChatLieu> timKiem(String ten);
    Boolean them(ChatLieu chatLieu);
    Boolean sua(ChatLieu chatLieu);
    Boolean xoa(ChatLieu chatLieu);
}
