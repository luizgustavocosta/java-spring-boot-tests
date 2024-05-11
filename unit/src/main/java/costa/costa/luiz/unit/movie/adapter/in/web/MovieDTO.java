package costa.costa.luiz.unit.movie.adapter.in.web;

public record MovieDTO(Long id, String title, int releasedYear, int minutes, String genres, double rating, String overview,
                       String director, String actors) {
}
