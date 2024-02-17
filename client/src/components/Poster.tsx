import { Link } from ".";

interface Props {
  movieId: number;
  imageUrl: string;
}

export default function Poster({ movieId, imageUrl }: Props) {
  return (
    <Link to={`/movie/${movieId}`}>
      <img
        className="h-auto rounded-md object-cover shadow-lg transition ease-in-out hover:scale-105 hover:cursor-pointer hover:shadow-2xl"
        src={imageUrl}
        alt="image"
      />
    </Link>
  );
}
