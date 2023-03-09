package com.example.demo.Folders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FolderRepository extends JpaRepository<Folder, Long>
{

    Folder findById(int id);

    @Transactional
    void deleteById(int id);
}
