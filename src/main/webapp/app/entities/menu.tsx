import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/template-my-suffix">
        Template
      </MenuItem>
      <MenuItem icon="asterisk" to="/carte-my-suffix">
        Carte
      </MenuItem>
      <MenuItem icon="asterisk" to="/reporting">
        Reporting
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
