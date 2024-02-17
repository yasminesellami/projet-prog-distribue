import { IMAGE_URL, cast } from ".";

interface Props {
  cast: cast;
}

export default function CastCard({ cast }: Props) {
  let src = "";
  if (cast.profilePath != "null") {
    src = IMAGE_URL + cast.profilePath;
  }
  return (
    <div className="min-w-[200px]">
      <img src={src} alt="profile" className="rounded-md" />
      <p className="text-sm">{cast.name}</p>
      <p className="text-stone-300 text-sm">{cast.character}</p>
    </div>
  );
}
