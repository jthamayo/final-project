const AddNewGroupComponent = ({ onClick }: { onClick: () => void }) => {
  return (
    <div className="flex items-center justify-center  bg-red-300 m-4 rounded-sm">
      <div className="addGroup flex items-center text-white justify-center gap-2 p-4">
        <p className="text-lg">You don't belong to any group</p>
        <svg className="icon size-8">
          <use xlinkHref="assets/icons.svg#exclamation"></use>
        </svg>
      </div>
      <button
        onClick={onClick}
        className="rounded-sm p-2 bg-blue-500 hover:bg-blue-800 transition-all text-white"
      >
        Create a group
      </button>
    </div>
  );
};

export default AddNewGroupComponent;
