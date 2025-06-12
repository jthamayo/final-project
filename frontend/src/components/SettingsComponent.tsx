import React from "react";

export const SettingsComponent = () => {
  return (
    <section className="p-4">
      <ul className="grid sm:grid-cols-2 lg:grid-cols-3 gap-2">
        <li className="cell">
          <p>Security</p>
          <svg className="icon size-10">
            <use xlinkHref="assets/icons.svg#settings"></use>
          </svg>
        </li>
        <li className="cell">
          Account Information
          <svg className="icon size-10">
            <use xlinkHref="assets/icons.svg#star"></use>
          </svg>
        </li>
        <li className="cell">
          Public profile
          <svg className="icon size-10">
            <use xlinkHref="assets/icons.svg#eye"></use>
          </svg>
        </li>
        <li className="cell">
          Report
          <svg className="icon size-10">
            <use xlinkHref="assets/icons.svg#delete-user"></use>
          </svg>
        </li>
        <li className="cell">
          Notifications
          <svg className="icon size-10">
            <use xlinkHref="assets/icons.svg#notification"></use>
          </svg>
        </li>
        <li className="cell">
          Delete account
          <svg className="icon size-10">
            <use xlinkHref="assets/icons.svg#delete-user"></use>
          </svg>
        </li>
      </ul>
    </section>
  );
};
