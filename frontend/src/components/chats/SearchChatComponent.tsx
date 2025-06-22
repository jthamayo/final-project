import { useState } from "react";

const SearchChatComponent = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [showSort, setShowSort] = useState(false);

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
    //
    console.log("Search:", e.target.value);
  };

  return (
    <div
      className={`p-4 rounded-xl shadow transition-all delay-75 bg-box ${
        showSort ? "" : ""
      }`}
    >
      <div className={`flex md:flex-row justify-center items-start md:gap-8}`}>
        <div className="w-full mr-4">
          <input
            type="text"
            placeholder="Search a chat"
            value={searchTerm}
            onChange={handleSearchChange}
            className="w-full px-4 py-2 border border-gray-300 bg-white rounded-md focus:outline-none focus:ring-1 focus:ring-base"
          />
        </div>
        <div className="flex items-center gap-2">
          <button
            className="rounded-md bg-white border border-gray-300"
            onClick={() => setShowSort(false)}
          >
            <svg className="w-10 h-10 cursor-pointer hover:fill-blue-500 transition-colors duration-200">
              <use xlinkHref="/assets/icons.svg#filter"></use>
            </svg>
          </button>
          <button
            className="rounded-md size-10 bg-white border border-gray-300"
            onClick={() => setShowSort((prev) => !prev)}
          >
            <svg className="size-8 m-auto cursor-pointer hover:fill-blue-500 transition-colors duration-200">
              <use xlinkHref="/assets/icons.svg#sort"></use>
            </svg>
          </button>
        </div>
      </div>
      {showSort && (
        <div className="mt-2 flex gap-2">
          <button
            className="rounded-md size-10 bg-white border border-gray-300"
            onClick={() => setShowSort((prev) => !prev)}
          >
            <svg className="size-8 m-auto cursor-pointer hover:fill-blue-500 transition-colors duration-200">
              <use xlinkHref="/assets/icons.svg#first"></use>
            </svg>
          </button>
          <button
            className="rounded-md size-10 bg-white border border-gray-300"
            onClick={() => setShowSort((prev) => !prev)}
          >
            <svg className="size-8 m-auto cursor-pointer hover:fill-blue-500 transition-colors duration-200">
              <use xlinkHref="/assets/icons.svg#last"></use>
            </svg>
          </button>
        </div>
      )}
    </div>
  );
};

export default SearchChatComponent;
