import { IMAGE_URL, image } from ".";

interface Props {
  images: image[];
}

export default function Images({ images }: Props) {
  return (
    <section>
      <h1 className="text-3xl">Images</h1>

      <ul className="flex gap-4 overflow-x-auto">
        {images.map((image, key) => {
          return (
            <img
              src={IMAGE_URL + image.filePath}
              alt="image"
              className="w-[1200px] rounded-md"
              key={key}
            />
          );
        })}
      </ul>
    </section>
  );
}
