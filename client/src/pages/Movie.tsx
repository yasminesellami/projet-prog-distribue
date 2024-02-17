import {
  useParams,
  useQuery,
  BackButton,
  MovieDetails,
  IMAGE_URL,
  Credits,
  getMovie,
  Images,
} from ".";

export default function Movie() {
  const { movieId } = useParams();
  const { isLoading, data } = useQuery(["movie"], () => getMovie(movieId));
  if (!isLoading) {
    return (
      <div
        style={{
          backgroundImage: `url(${IMAGE_URL + data.movie.backdropPath})`,
        }}
        className="bg-cover"
      >
        <div className="flex flex-col gap-4 backdrop-blur-2xl bg-slate-800/40 p-6 lg:p-12 text-white">
          <BackButton url="/" />
          <MovieDetails
            imageUrl={IMAGE_URL + data.movie.posterPath}
            title={data.movie.title}
            overview={data.movie.overview}
            genres={data.movie.genres}
            releaseDate={data.movie.releaseDate}
          />
          <Credits casts={data.cast} crews={data.crew} />
          <Images images={data.images} />
        </div>
      </div>
    );
  }
}
