package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Entity.Authors;
import com.example.library_system.Repository.AuthorRepository;
import com.example.library_system.Service.ServiceInterface.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Authors> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Authors> findByLastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }
    @Override
    public Authors createAuthor(Authors author){
        return authorRepository.save(author);
    }
}
