const UserAvatar = ({ url }: { url: string }) => {
  return (
    <div className="avatar min-h-10 aspect-square bg-base rounded-md h-full">
      {url ? (
        <img
          src={url}
          alt="profile picture"
          className="object-cover w-full h-full rounded-lg"
        />
      ) : (
        <svg className="plus-icon w-full h-full text-muted flex items-center justify-center p-[20%]">
          <use xlinkHref="assets/icons.svg#user"></use>
        </svg>
      )}
    </div>
  );
};

export default UserAvatar;
