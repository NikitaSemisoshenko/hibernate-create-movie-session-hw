package mate.academy.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.dao.MovieSessionDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.MovieSession;
import mate.academy.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public MovieSession add(MovieSession entity) {
        return movieSessionDao.add(entity);
    }

    @Override
    public MovieSession get(Long id) {
        Optional<MovieSession> movieSessionOptional = movieSessionDao.get(id);
        return movieSessionOptional.orElseThrow(() ->
                new RuntimeException("Movie session with id " + id + "is not exist at DB"));
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        List<MovieSession> sessions = movieSessionDao.getAllMovies();
        return sessions.stream()
                .filter(x -> x.getShowTime().toLocalDate().equals(date)
                        && x.getMovie().getId().equals(movieId))
                .collect(Collectors.toList());
    }
}