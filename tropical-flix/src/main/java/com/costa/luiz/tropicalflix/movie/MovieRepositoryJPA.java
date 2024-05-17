package com.costa.luiz.tropicalflix.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryJPA extends JpaRepository<Movie, Long> {
}