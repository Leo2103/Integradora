package mx.edu.utez.integradora.service.impl;

import mx.edu.utez.integradora.model.Role;
import mx.edu.utez.integradora.repository.RoleRepository;
import mx.edu.utez.integradora.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public Role buscarAuthority(String authority) {
        return repository.findByAuthority(authority);
    }
}
