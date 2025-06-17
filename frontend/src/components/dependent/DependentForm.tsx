import { useState } from "react";
import {
  addDependent,
  Dependent,
  AllergyOptions,
  Allergy,
} from "../../services/DependentService";

const DependentForm = () => {
  const [state, setState] = useState<"error" | "pending" | "success">(
    "pending"
  );
  const [form, setForm] = useState<Dependent>({
    firstName: "",
    lastName: "",
    allergies: [],
    isSpecialNeeds: false,
    isAllergic: false,
    birthDate: "",
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value, type } = e.target;

    if (type === "checkbox" && e.target instanceof HTMLInputElement) {
      const checked = e.target.checked;

      if (name === "allergies") {
        setForm((prev) => ({
          ...prev,
          allergies: checked
            ? [...prev.allergies, value as Allergy]
            : prev.allergies.filter((a) => a !== value),
        }));
      } else {
        setForm((prev) => ({
          ...prev,
          [name]: checked,
        }));
      }
    } else {
      setForm((prev) => ({
        ...prev,
        [name]: value,
      }));
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await addDependent(form);
      setState("success");
    } catch (err) {
      console.error("Failed to submit dependent", err);
      setState("error");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="p-4 flex flex-col gap-2 bg-white rounded-md w-full"
    >
      {state !== "success" && (
        <>
          <label htmlFor="firstName" className="text-xl field">
            First Name
          </label>
          <input
            type="text"
            id="firstName"
            name="firstName"
            placeholder="First Name"
            value={form.firstName}
            onChange={handleChange}
            className="box"
            required
          />

          <label htmlFor="lastName" className="text-xl field">
            Last Name
          </label>
          <input
            type="text"
            id="lastName"
            name="lastName"
            placeholder="Last Name"
            value={form.lastName}
            onChange={handleChange}
            className="box"
            required
          />

          <label htmlFor="birthDate" className="text-xl field">
            Birth Date
          </label>
          <input
            type="date"
            id="birthDate"
            name="birthDate"
            value={form.birthDate}
            onChange={handleChange}
            className="box"
            required
          />

          <label htmlFor="isAllergic" className="text-xl field">
            <input
              type="checkbox"
              id="isAllergic"
              name="isAllergic"
              checked={form.isAllergic}
              onChange={handleChange}
              className="mr-2"
            />
            Allergic
          </label>

          {form.isAllergic && (
            <>
              <label className="text-xl field">Select Allergies</label>
              <div className="grid grid-cols-2 gap-1">
                {AllergyOptions.map((option) => (
                  <label key={option} className="flex items-center gap-2">
                    <input
                      type="checkbox"
                      name="allergies"
                      value={option}
                      checked={form.allergies.includes(option)}
                      onChange={handleChange}
                    />
                    <span>{option.replace("_", " ")}</span>
                  </label>
                ))}
              </div>
            </>
          )}

          <label htmlFor="isSpecialNeeds" className="text-xl field">
            <input
              type="checkbox"
              id="isSpecialNeeds"
              name="isSpecialNeeds"
              checked={form.isSpecialNeeds}
              onChange={handleChange}
              className="mr-2"
            />
            Special Needs
          </label>

          <button type="submit" className="dark-box p-0">
            Save Dependent
          </button>
        </>
      )}
      {state === "success" && (
        <div className="bg-green-300 flex rounded-lg p-4 mt-8">
          <p className="text-white">You have successfully added a Dependent</p>
          <svg className="plus-icon size-7 text-muted flex items-center justify-center">
            <use xlinkHref="assets/icons.svg#tick"></use>
          </svg>
        </div>
      )}
      {state === "error" && (
        <p className="error bottom-0 left-1/2 -translate-x-1/2">
          Please try again later
        </p>
      )}
    </form>
  );
};

export default DependentForm;
