import {
  IMAGE_URL,
  listNowPlayingMovies,
  Poster,
  useQuery,
  nowPlayingMovie,
  useState,
  useEffect,
  useMemo,
  Skeleton,
} from ".";

export default function MovieLibrary() {
  const [movies, setMovies] = useState<nowPlayingMovie[]>([]);
  const [filterText, setFilter] = useState("");

  const { isLoading, data, status } = useQuery(["movies"], () =>
    listNowPlayingMovies()
  );

  useEffect(() => {
    if (status == "success") {
      setMovies(data.results);
    }
  }, [status, data]);

  const filterMovies = (event: React.ChangeEvent<HTMLInputElement>) => {
    setFilter(event.target.value);
  };

  const filtered = useMemo(() => {
    return movies.filter((movie) => {
      return movies.length > 0
        ? movie.title.toLowerCase().includes(filterText.toLowerCase())
        : true;
    });
  }, [filterText, movies]);

  const skeletons = [];

  // Boucle pour créer 10 div
  for (let i = 0; i < 20; i++) {
    skeletons.push(<Skeleton />);
  }

  return (
    <div className="flex flex-col gap-6 p-12">
      <div className="flex justify-between flex-col sm:flex-row">
        <h1 className="dark:text-white text-4xl">🎬🍿 Movie library</h1>
        <input
          type="text"
          className="pl-4 rounded-full min-w-full sm:min-w-[20rem] dark:bg-gray-700 m-4 sm:m-0"
          onChange={filterMovies}
          placeholder="🔎 Search for movie"
        />
      </div>
      <div className="grid lg:grid-cols-6 md:grid-cols-4 sm:grid-cols-2 gap-4">
        {!isLoading
          ? filtered.map((result: nowPlayingMovie, key: number) => (
              <Poster
                key={key}
                movieId={result.id}
                imageUrl={IMAGE_URL + result.poster_path}
              />
            ))
          : skeletons}
      </div>
    </div>
  );
}
