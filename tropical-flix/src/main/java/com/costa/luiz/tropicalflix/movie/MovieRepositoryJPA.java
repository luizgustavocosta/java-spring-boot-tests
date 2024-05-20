package com.costa.luiz.tropicalflix.movie;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepositoryJPA extends JpaRepository<Movie, Long> {

    List<Movie> findAllByRatingIsGreaterThanEqual(PageRequest pageRequest, double rating);
}
