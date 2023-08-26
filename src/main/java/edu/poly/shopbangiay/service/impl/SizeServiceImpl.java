package edu.poly.shopbangiay.service.impl;

import edu.poly.shopbangiay.model.Size;
import edu.poly.shopbangiay.repository.SizeRepository;
import edu.poly.shopbangiay.service.SizeService;

import java.util.List;

public class SizeServiceImpl implements SizeService {
    SizeRepository sizeRepository = new SizeRepository();

    @Override
    public List<Size> getList() {
        return sizeRepository.getList();
    }

    @Override
    public Size getOne(String ma) {
        return null;
    }

    @Override
    public List<Size> timKiem(Integer soSize) {
        return sizeRepository.timKiem(soSize);
    }


    @Override
    public Boolean them(Size size) {
        return sizeRepository.them(size);
    }

    @Override
    public Boolean sua(Size size) {
        return sizeRepository.sua(size);
    }

    @Override
    public Boolean xoa(Size size) {
        return sizeRepository.xoa(size);
    }
}
