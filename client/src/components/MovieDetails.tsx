interface Props {
  imageUrl: string;
  title: string;
  overview: string;
  genres: { name: string }[];
  releaseDate: string;
}

export default function MovieDetails({
  imageUrl,
  title,
  overview,
  genres,
  releaseDate,
}: Props) {
  return (
    <section className="flex flex-col sm:flex-row sm:items-end gap-2">
      <img
        src={imageUrl}
        alt="poster"
        className="rounded-md object-cover m-auto"
        width={300}
      />
      <div>
        <h1 className="text-4xl">{title}</h1>
        <p className="text-md">{overview}</p>
        <ul className="mt-2 text-md italic">
          {genres.map((genre, key) => (
            <li key={key} className="inline">
              {key !== genres.length - 1 ? genre.name + ", " : genre.name}
            </li>
          ))}
        </ul>
        <time>{releaseDate}</time>
      </div>
    </section>
  );
}
